package com.backend.Backend_supermarket.service.impl;

import com.backend.Backend_supermarket.repository.PartnerRepository;
import com.backend.Backend_supermarket.responses.PartnerResponse;
import com.backend.Backend_supermarket.service.PartnerService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // dùng để inject các bean để sử dụng
public class PartnerServiceImpl implements PartnerService{
    
    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponse> getAllPartner() {
        return partnerRepository.findAll()
                .stream()
                .map(PartnerResponse::fromPartner)
                .collect(Collectors.toList());
    }
}
