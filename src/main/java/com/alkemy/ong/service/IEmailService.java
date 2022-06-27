package com.alkemy.ong.service;

public interface IEmailService {

    public void sendRegisterConfirmation(String receiverEmail, String name) throws Exception;

    public void sendText(String receiver, String subject, String content) throws Exception;

}
