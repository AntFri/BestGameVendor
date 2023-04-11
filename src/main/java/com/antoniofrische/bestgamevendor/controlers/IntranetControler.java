package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import com.antoniofrische.bestgamevendor.services.ProductService;
import com.antoniofrische.bestgamevendor.services.RegionService;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/intranet")
public class IntranetControler {

    @Autowired
    private ProductService prodServ;
    @Autowired
    private UserService userServ;

    @Autowired
    private RegionService regionServ;

    Logger logger = LoggerFactory.getLogger(PageControler.class);


    @GetMapping("/adminPanel")
    public String adminPanel(Model model){
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = userServ.userFindByEmail(detailUser.getUsername());
        model.addAttribute("title", "AdminPanel of user:" + currentUser.getNombre());
        model.addAttribute("user", currentUser);
        return "security/admin/adminPanel";
    }


    @GetMapping("/userlist")
    public String listUsers(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<UserEntity> userPage = userServ.userFindAllPage(PageRequest.of(currentPage-1,pageSize));

        model.addAttribute("userPage", userPage);

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<RegionEntity> regiones = regionServ.regionFindAll();
        UserEntity userObj = new UserEntity();
        model.addAttribute("regiones", regiones);
        model.addAttribute("userObj", userObj);
        return "security/admin/userList";
    }

    @GetMapping("/prodlist")
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
        return "security/admin/prodList";
    }

    @GetMapping("/platformlist")
    public String listPlatform(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listPlatPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/platformList";
    }

    @GetMapping("/publisherlist")
    public String listPublisher(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listPubPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/publisherList";
    }

    @GetMapping("/salewebsitelist")
    public String listSaleWebsite(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listSellPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/salewebsiteList";
    }

    @GetMapping("/gernelist")
    public String listGenre(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listGenPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/gerneList";
    }

    @GetMapping("/regionlist")
    public String listRegion(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listRegPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/regionList";
    }

    @GetMapping("/salechangelist")
    public String listSaleChange(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listRedPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/saleChangeList";
    }
    @GetMapping("/reviewlist")
    public String listReview(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ProductosEntity> Page = prodServ.prodFindAllPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("listRewPage", Page);

        int totalPages = Page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "security/admin/reviewList";
    }

    @PostMapping("/userlist/delete")
    public String userDelete(@RequestParam("idU") Long idU, RedirectAttributes redirectAttributes){
        try {
            userServ.userDelete(idU);
        } catch (EntityNotFound e) {
            logger.error("User not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/userlist";
        }
        logger.info("Delete!");
        redirectAttributes.addFlashAttribute("Message", "User Deleted!");
        return "redirect:/intranet/userlist";
    }

    @PostMapping("/userlist/edit")
    public String userEdit(UserEntity userObj , RedirectAttributes redirectAttributes){
        try {
            logger.info("go to serv!");
            userServ.userEdit(userObj, (long) userObj.getIdUsuario());
        } catch (EntityNotFound | EntityAlreadyExists e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/userlist";
        }


        logger.info("edited!");
        return "redirect:/intranet/userlist";
    }

    @PostMapping("/userlist/add")
    public String processRegister(UserEntity useradd, RedirectAttributes redirectAttributes) {
        try {
            userServ.processReg(useradd);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/userlist";
        }catch (EntityAlreadyExists | UserAgeToLow uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/userlist";
        }
    }
}
