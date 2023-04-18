package com.antoniofrische.bestgamevendor.controlers.intranet;


import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import com.antoniofrische.bestgamevendor.services.PublisherService;
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
@RequestMapping("/intranet/publisherlist")
public class PublisherControler {
    Logger logger = LoggerFactory.getLogger(PublisherControler.class);

    @Autowired
    private PublisherService publisherServ;
    @Autowired
    private RegionService regionServ;

    @GetMapping("")
    public String listPublisher(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<PublisherEntity> Page = publisherServ.publisherFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listPubPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("regiones", regionServ.regionFindAll());
        return "security/admin/publisherList";
    }

    @PostMapping("/delete")
    public String regionDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            publisherServ.publisherDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Publisher Deleted!");
            return "redirect:/intranet/publisherlist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/publisherlist";
        }
    }

    @PostMapping("/edit")
    public String regionEdit(PublisherEntity entity , RedirectAttributes redirectAttributes){
        try {
            publisherServ.publisherEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Publisher edited!");
            return "redirect:/intranet/publisherlist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/publisherlist";
        }
    }

    @PostMapping("/add")
    public String regionAdd(PublisherEntity entity, RedirectAttributes redirectAttributes) {
        try {
            publisherServ.publisherSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/publisherlist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/publisherlist";
        }
    }
}
