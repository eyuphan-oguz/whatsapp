package com.whatsapp.whatsapp.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupChatRequest {
    private List<Integer> userIds;

    private String chat_name;

    private String chat_image;
}
