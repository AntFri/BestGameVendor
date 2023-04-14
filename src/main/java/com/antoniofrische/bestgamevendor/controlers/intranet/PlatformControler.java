package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.services.PlatformService;
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
@RequestMapping("/intranet/platformlist")
public class PlatformControler {
    Logger logger = LoggerFactory.getLogger(PlatformControler.class);

    @Autowired
    private PlatformService platformServ;

    @GetMapping("")
    public String listPlatform(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<PlataformasEntity> Page = platformServ.platformFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listPlatPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/platformList";
    }

    @PostMapping("/delete")
    public String platformDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            platformServ.platformDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Platform Deleted!");
            return "redirect:/intranet/platformlist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/platformlist";
        }
    }

    @PostMapping("/edit")
    public String platformEdit(PlataformasEntity entity , RedirectAttributes redirectAttributes){
        try {
            platformServ.platformEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Plaform edited!");
            return "redirect:/intranet/platformlist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/platformlist";
        }
    }

    @PostMapping("/add")
    public String platformAdd(PlataformasEntity entity, RedirectAttributes redirectAttributes) {
        try {
            platformServ.platformSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/platformlist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/platformlist";
        }
    }
}
