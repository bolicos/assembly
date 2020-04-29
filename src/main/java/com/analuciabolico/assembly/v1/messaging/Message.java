package com.analuciabolico.assembly.v1.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements IMessage {
    private String nickname;
    private String email;
}
