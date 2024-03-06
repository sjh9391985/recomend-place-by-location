package dev.be.project.direction.controller;

import dev.be.project.direction.dto.InputDto;
import dev.be.project.direction.entity.Direction;
import dev.be.project.direction.service.DirectionService;
import dev.be.project.pharmacy.service.PharmacyRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DirectionController {

    private final DirectionService directionService;
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    @GetMapping("/dir/{encodedId}")
    public String searchDirection(@PathVariable("encodedId") String encodedId) {
        Direction direction = directionService.findById(encodedId);

        String params = String.join(
                ",",
                direction.getTargetPharmacyName(),
                String.valueOf(direction.getTargetLatitude()),
                String.valueOf(direction.getTargetLongtitude())
        );

        String result = UriComponentsBuilder.fromHttpUrl(DIRECTION_BASE_URL + params)
                .toUriString();

        return "redirect:"+result;
    }


}
