package org.img.service;

/**
 * ITransferService
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:03:00
 */
public interface ITransferService {

    void transfer(String fromCardNo, String toCardNo, int money);
}
