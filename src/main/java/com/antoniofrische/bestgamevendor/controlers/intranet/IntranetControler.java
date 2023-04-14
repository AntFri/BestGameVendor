package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.*;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import com.antoniofrische.bestgamevendor.services.*;
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
    private UserService userServ;

    Logger logger = LoggerFactory.getLogger(IntranetControler.class);

    @GetMapping({"","/adminPanel","/"})
    public String adminPanel(Model model){
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = userServ.userFindByEmail(detailUser.getUsername());
        model.addAttribute("title", "AdminPanel of user:" + currentUser.getNombre());
        model.addAttribute("user", currentUser);
        return "security/admin/adminPanel";
    }

}
