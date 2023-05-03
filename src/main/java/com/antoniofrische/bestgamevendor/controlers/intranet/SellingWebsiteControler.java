package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.SellingWebsiteEntity;
import com.antoniofrische.bestgamevendor.services.SellWebsiteService;
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
@RequestMapping("/intranet/sallingwebsitelist")
public class SellingWebsiteControler {
    Logger logger = LoggerFactory.getLogger(SellingWebsiteControler.class);

    @Autowired
    private SellWebsiteService cellWebsiteServ;

    @GetMapping("")
    public String listSaleWebsite(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("searchKey") Optional<String> searchKey) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String search = searchKey.orElse(null);

        Page<SellingWebsiteEntity> Page = cellWebsiteServ.salesWebFindAllPageSearch(PageRequest.of(currentPage - 1, pageSize), search);

        model.addAttribute("listSellPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("searchKey", search);
        return "security/admin/salewebsiteList";
    }

    @PostMapping("/delete")
    public String sellingDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            cellWebsiteServ.salesWebDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Selling Website Deleted!");
            return "redirect:/intranet/sallingwebsitelist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/sallingwebsitelist";
        }
    }

    @PostMapping("/edit")
    public String sellingEdit(SellingWebsiteEntity entity , RedirectAttributes redirectAttributes){
        try {
            cellWebsiteServ.salesWebEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Selling Website Edited!");
            return "redirect:/intranet/sallingwebsitelist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/sallingwebsitelist";
        }

    }

    @PostMapping("/add")
    public String sellingAdd(SellingWebsiteEntity entity, RedirectAttributes redirectAttributes) {
        try {
            cellWebsiteServ.salesWebSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/sallingwebsitelist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/sallingwebsitelist";
        }
    }
}
