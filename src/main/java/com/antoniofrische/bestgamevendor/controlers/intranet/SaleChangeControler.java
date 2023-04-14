package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.services.CellWebsiteService;
import com.antoniofrische.bestgamevendor.services.ListSalesService;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/intranet/salechangelist")
public class SaleChangeControler {
    Logger logger = LoggerFactory.getLogger(SaleChangeControler.class);

    @Autowired
    private ListSalesService salesServ;
    @Autowired
    private CellWebsiteService cellWebsiteServ;
    @Autowired
    private ProductService prodServ;

    @GetMapping("")
    public String listSaleChange(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ListaRebajasproductosEntity> Page = salesServ.salesFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listRedPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("cellWebsites", cellWebsiteServ.salesWebAll());
        model.addAttribute("productos", prodServ.prodAll());
        return "security/admin/salechangelist";
    }
    @PostMapping("/delete")
    public String salesDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            salesServ.salesDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Sale Deleted!");
            return "redirect:/intranet/salechangelist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/salechangelist";
        }
    }

    @PostMapping("/edit")
    public String salesEdit(ListaRebajasproductosEntity entity , RedirectAttributes redirectAttributes){
        try {
            salesServ.salesEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Sale Edited!");
            return "redirect:/intranet/salechangelist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/salechangelist";
        }
    }

    @PostMapping("/add")
    public String salesAdd(ListaRebajasproductosEntity entity, RedirectAttributes redirectAttributes) {
        try {
            salesServ.salesSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/salechangelist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/salechangelist";
        }
    }
}
