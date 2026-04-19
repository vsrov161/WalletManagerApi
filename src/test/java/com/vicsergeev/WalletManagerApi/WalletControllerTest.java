package com.vicsergeev.WalletManagerApi;

/*
 * Created by Vic
 * 18.04.2026
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vicsergeev.WalletManagerApi.controller.WalletController;
import com.vicsergeev.WalletManagerApi.dto.*;
import com.vicsergeev.WalletManagerApi.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private WalletService walletService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createWallet() throws Exception {
        WalletCreateRequest request = new WalletCreateRequest(
                1L,
                "testWalletTitle"
        );
        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(1L);
        walletDto.setWalletTitle("testWalletTitle");
        when(walletService.create(any(WalletCreateRequest.class))).thenReturn(walletDto);

        mockMvc.perform(post("/api/v1/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.balance").value(1L))
                .andExpect(jsonPath("$.walletTitle").value("testWalletTitle"));
    }

    @Test
    void getAllWallets() throws Exception {

        UUID uuid = UUID.fromString("1ea256d7-6ba4-4580-bbf3-0d53da820020");

        List<WalletDto> walletDtoList = new ArrayList<>();
        walletDtoList.add(new WalletDto(
                uuid,
                1L,
                "test1"
        ));

        when(walletService.findAll()).thenReturn(walletDtoList);
        mockMvc.perform(get("/api/v1/wallets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].balance").value(1L))
                .andExpect(jsonPath("$[0].walletTitle").value("test1"))
                .andExpect(jsonPath("$[0].id").value(uuid.toString())
        );
    }

    @Test
    void getWalletById() throws Exception {
        UUID uuid = UUID.fromString("1ea256d7-6ba4-4580-bbf3-0d53da820020");
        WalletDto walletDto = new WalletDto(
                uuid,
                1L,
                "test1"
        );

        when(walletService.findById(any(UUID.class))).thenReturn(walletDto);
        mockMvc.perform(get("/api/v1/wallets/" + uuid)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(1L))
                .andExpect(jsonPath("$.walletTitle").value("test1"))
                .andExpect(jsonPath("$.id").value(uuid.toString()));
    }

    @Test
    void updateWalletTitle() throws Exception {
        UUID uuid = UUID.fromString("1ea256d7-6ba4-4580-bbf3-0d53da820020");

        WalletUpdateRequest request = new WalletUpdateRequest();
        request.setUpdatedWalletTitle("testUpdatedWalletTitle");

        WalletDto walletDto = new WalletDto(
                uuid,
                1L,
                "updated title"
        );

        when(walletService.updateById(any(UUID.class), any(WalletUpdateRequest.class))).thenReturn(walletDto);

        mockMvc.perform(patch("/api/v1/wallets/" + uuid)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.walletTitle").value("updated title"))
                .andExpect(jsonPath("$.id").value(uuid.toString()));
    }

    @Test
    void deleteById() throws Exception {
        UUID uuid = UUID.fromString("1ea256d7-6ba4-4580-bbf3-0d53da820020");
        mockMvc.perform(delete("/api/v1/wallets/" + uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(delete("/api/v1/wallets/erase"))
                .andExpect(status().isNoContent());
    }

    @Test
    void walletOperationDeposit()  throws Exception {
        OperationTypeRequest operationTypeRequest = new OperationTypeRequest();
        operationTypeRequest.setWalletId(UUID.randomUUID().toString());
        operationTypeRequest.setOperationType(OperationType.DEPOSIT);
        operationTypeRequest.setAmount(1L);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operationTypeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_status").value("success!"));
    }
}
