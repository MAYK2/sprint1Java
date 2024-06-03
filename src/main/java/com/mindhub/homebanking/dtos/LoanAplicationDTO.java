package com.mindhub.homebanking.dtos;

public record LoanAplicationDTO(Long id, double quantity, int payments, String destinationAccount) {
}
