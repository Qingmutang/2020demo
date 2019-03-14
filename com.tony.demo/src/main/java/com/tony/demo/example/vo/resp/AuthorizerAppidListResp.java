package com.tony.demo.example.vo.resp;

import java.io.Serializable;
import java.util.List;

import com.tony.demo.misc.base.PageInfo;

import lombok.Data;

@Data
public class AuthorizerAppidListResp implements Serializable{

	private static final long serialVersionUID = -4898353944052439639L;

    List<AuthorizerAppidResp> authorizerAppidList;
    
    private PageInfo pageInfo;
}
