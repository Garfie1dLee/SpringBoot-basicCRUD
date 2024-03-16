package com.yu.controller;

import com.yu.pojo.PortalVo;
import com.yu.service.HeadlineService;
import com.yu.service.TypeService;
import com.yu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;
    @GetMapping("findAllTypes")
    public Result findAllTypes()
    {
        Result result=typeService.findAllTypes();
        return result;
    }
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalvo)
    {
        Result result=headlineService.findNewsPage(portalvo);
        return result;
    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam Integer hid)
    {
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }
}
