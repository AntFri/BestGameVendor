package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.services.RegionService;
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
@RequestMapping("/intranet/regionlist")
public class RegionControler {
    Logger logger = LoggerFactory.getLogger(RegionControler.class);

    @Autowired
    private RegionService regionServ;

    @GetMapping("")
    public String listRegion(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<RegionEntity> Page = regionServ.regionFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listRegPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/regionList";
    }

    @PostMapping("/delete")
    public String regionDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            regionServ.regionDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Region Deleted!");
            return "redirect:/intranet/regionlist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/regionlist";
        }
    }

    @PostMapping("/edit")
    public String regionEdit(RegionEntity entity , RedirectAttributes redirectAttributes){
        try {
            regionServ.regionEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Region edited!");
            return "redirect:/intranet/regionlist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/regionlist";
        }
    }

    @PostMapping("/add")
    public String regionAdd(RegionEntity entity, RedirectAttributes redirectAttributes) {
        try {
            regionServ.regionSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/regionlist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/regionlist";
        }
    }
}
