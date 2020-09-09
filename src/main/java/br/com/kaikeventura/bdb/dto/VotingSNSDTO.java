package br.com.kaikeventura.bdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VotingSNSDTO implements Serializable {

    private static final long serialVersionUID = -6272716077418559244L;

    private String Type;
    private String MessageId;
    private String Token;
    private String TopicArn;
    private String Message;
    private String SubscribeURL;
    private String Timestamp;
    private String SignatureVersion;
    private String Signature;
    private String SigningCertURL;
}
