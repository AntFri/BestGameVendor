package com.antoniofrische.bestgamevendor.controlers.intranet;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/intranet/regionlist")
public class RegionControler {
    Logger logger = LoggerFactory.getLogger(PageControler.class);

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
}
