package com.ldlood.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Ldlood on 2017/8/20.
 */

@Data
public class MessageVO {


    private Integer userNum;


    private Integer type;


    private String message;

}