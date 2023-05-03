package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.services.GenreService;
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
@RequestMapping("/intranet/gernelist")
public class GenreControler {
    Logger logger = LoggerFactory.getLogger(GenreControler.class);

    @Autowired
    private GenreService genreServ;

    @GetMapping("")
    public String listGenre(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("searchKey") Optional<String> searchKey) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String search = searchKey.orElse(null);

        Page<GenreEntity> Page = genreServ.genreFindAllPageSearch(PageRequest.of(currentPage - 1, pageSize), search);

        model.addAttribute("listGenPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("searchKey", search);
        return "security/admin/gerneList";
    }

    @PostMapping("/delete")
    public String genreDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            genreServ.genreDelet(id);
            logger.info("Delete!");
            redirectAttributes.addFlashAttribute("Message", "Genre Deleted!");
            return "redirect:/intranet/gernelist";
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/gernelist";
        }
    }

    @PostMapping("/edit")
    public String genreEdit(GenreEntity entity , RedirectAttributes redirectAttributes){
        try {
            genreServ.genreEdit(entity);
            logger.info("edited!");
            redirectAttributes.addFlashAttribute("Message", "Genre edited!");
            return "redirect:/intranet/gernelist";
        } catch (EntityNotFound | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/gernelist";
        }
    }

    @PostMapping("/add")
    public String genreAdd(GenreEntity entity, RedirectAttributes redirectAttributes) {
        try {
            genreServ.genreSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/gernelist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/gernelist";
        }
    }
}
