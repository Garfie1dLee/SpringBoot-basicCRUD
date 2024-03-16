package com.yu.controller;

import com.yu.pojo.Headline;
import com.yu.service.HeadlineService;
import com.yu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;
    @PostMapping("publish")
    public Result publish(@RequestHeader String token,
                          @RequestBody Headline headline)
    {
        Result result=headlineService.publish(token,headline);
        return result;
    }
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@RequestParam Integer hid)
    {
        Result result=headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline)
    {
        Result result=headlineService.updateHeadline(headline);
        return result;
    }
    @PostMapping("removeByHid")
    public Result removeByHid(@RequestParam Integer hid)
    {
        Result result=headlineService.removeByHid(hid);
        return result;
    }
}
