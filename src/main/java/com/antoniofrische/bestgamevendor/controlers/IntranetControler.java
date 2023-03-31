package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.repositorios.IRegionRepository;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import com.antoniofrische.bestgamevendor.services.ProductService;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/intranet")
public class IntranetControler {

    @Autowired
    private ProductService prodServ;
    @Autowired
    private UserService userServ;

    @Autowired
    private IRegionRepository iRegionRepository;

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
    public String userlist(Model model){
        List<UserEntity> users = userServ.findAllUsers();
        List<RegionEntity> regiones = iRegionRepository.findAll();
        UserEntity userObj = new UserEntity();
        model.addAttribute("regiones", regiones);
        model.addAttribute("userList", users);
        model.addAttribute("userObj", userObj);
        return "security/admin/userlist";
    }

    @PostMapping("/userlist/delete")
    public String userDelete(@RequestParam("idU") Long idU, RedirectAttributes redirectAttributes){
        try {
            userServ.userDelete(idU);
        } catch (UserNotFound e) {
            logger.error("User not exist!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/userlist";
        }
        logger.info("Delete!");
        redirectAttributes.addFlashAttribute("Message", "User Deleted!");
        return "redirect:/intranet/userlist";
    }

    @PostMapping("/userlist/edit")
    public String userEdit(@RequestParam("idU") Long idU, UserEntity userObj , RedirectAttributes redirectAttributes){
        try {
            logger.info("go to serv!");
            userServ.userEdit(userObj, idU);
        } catch (UserNotFound | UserAlreadyExists e) {
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
        }catch (UserAlreadyExists | UserAgeToLow uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/userlist";
        }
    }
}
