package com.example.sesia2.dto;

import java.util.UUID;

public record FlatResponseDto(UUID id,
                              String name,
                              String city,
                              int bed,
                              int bath,
                              int price,
                              String imageUrl) {
}
