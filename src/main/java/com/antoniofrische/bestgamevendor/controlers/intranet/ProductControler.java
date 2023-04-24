package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.models.ProductImageEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.services.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/intranet/prodlist")
public class ProductControler {
    Logger logger = LoggerFactory.getLogger(ProductControler.class);

    @Autowired
    private ProductService prodServ;
    @Autowired
    private PublisherService publisherServ;
    @Autowired
    private GenreService genreServ;
    @Autowired
    private RegionService regionServ;
    @Autowired
    private PlatformService platformServ;
    @Autowired
    private ProductImageService prodImgServ;

    @GetMapping("")
    public String listProds(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> prodPage = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("prodPage", prodPage);

        int totalPages = prodPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("regiones", regionServ.regionFindAll());
        model.addAttribute("publishers", publisherServ.publisherAll());
        model.addAttribute("genres", genreServ.genreAll());
        model.addAttribute("platforms", platformServ.platformAll());
        model.addAttribute("photos", prodImgServ.imageFindAll());
        return "security/admin/prodList";
    }

    @PostMapping("/images/upload")
    public String uploadImage(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file, @RequestParam("imgName") String name) {
        try {
            prodImgServ.save(file,name);
            redirectAttributes.addFlashAttribute("Message", "Image uploaded!");
            return "redirect:/intranet/prodlist";
        } catch (Exception e) {
            System.out.println("error: "+ e.getMessage());
            redirectAttributes.addFlashAttribute("Message", "Image could not be uploded!");
            return "redirect:/intranet/prodlist";
        }
    }

    @PostMapping("/delete")
    public String prodDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        try {
            prodServ.prodDelet(id);
        } catch (EntityNotFound e) {
            logger.error("Product not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/prodlist";
        }
        logger.info("Delete!");
        redirectAttributes.addFlashAttribute("Message", "Product Deleted!");
        return "redirect:/intranet/prodlist";
    }

    @PostMapping("/edit")
    public String prodEdit(ProductosEntity entity , RedirectAttributes redirectAttributes){
        try {
            prodServ.prodEdit(entity);
        } catch (EntityNotFound  | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/prodlist";
        }

        logger.info("edited!");
        return "redirect:/intranet/prodlist";
    }

    @PostMapping("/add")
    public String prodAdd(ProductosEntity entity, RedirectAttributes redirectAttributes) {
        try {
            prodServ.prodSave(entity);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/prodlist";
        }catch (EntityAlreadyExists | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/prodlist";
        }
    }
}
