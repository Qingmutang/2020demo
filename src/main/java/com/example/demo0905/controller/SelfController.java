package com.example.demo0905.controller;

import com.example.demo0905.service.SelfManagerService;

/**
 * @author qinglin
 * @create 2020-09-05 11:24
 */
public class SelfController extends BaseController {

    SelfManagerService selfManagerService;

    public String  transfer(String input){

        return   handler(context -> selfManagerService.transfer(input,context.getStudent()));

    }
}
