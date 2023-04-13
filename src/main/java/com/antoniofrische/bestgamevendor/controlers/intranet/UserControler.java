package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.services.RegionService;
import com.antoniofrische.bestgamevendor.services.UserService;
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
@RequestMapping("/intranet/userlist")
public class UserControler {
    Logger logger = LoggerFactory.getLogger(PageControler.class);
    @Autowired
    private UserService userServ;
    @Autowired
    private RegionService regionServ;

    @GetMapping("")
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

    @PostMapping("/delete")
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

    @PostMapping("/edit")
    public String userEdit(UserEntity userObj , RedirectAttributes redirectAttributes){
        try {
            logger.info("go to serv!");
            userServ.userEdit(userObj, (long) userObj.getIdUsuario());
        } catch (EntityNotFound | EntityAlreadyExists | FormFieldEmpty e) {
            logger.error("not worked!");
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/intranet/userlist";
        }


        logger.info("edited!");
        return "redirect:/intranet/userlist";
    }

    @PostMapping("/add")
    public String userAdd(UserEntity useradd, RedirectAttributes redirectAttributes) {
        try {
            userServ.processReg(useradd);
            redirectAttributes.addFlashAttribute("Message", "Sucessfully created!");
            return "redirect:/intranet/userlist";
        }catch (EntityAlreadyExists | UserAgeToLow | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("Message", uae.getMessage());
            return "redirect:/intranet/userlist";
        }
    }
}
