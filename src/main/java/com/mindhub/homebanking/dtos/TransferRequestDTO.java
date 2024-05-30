package com.mindhub.homebanking.dtos;

public record TransferRequestDTO(String fromAccountNumber,
                                 String toAccountNumber,
                                 Double amount,
                                 String description) {
}
