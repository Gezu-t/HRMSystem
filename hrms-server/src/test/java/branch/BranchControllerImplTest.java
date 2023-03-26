package branch;

import et.hrms.controller.BranchController;
import et.hrms.dal.dto.BranchDTO;
import et.hrms.service.BranchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchControllerImplTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchController branchController;

    @Test
    void testCreateBranch() {
        BranchDTO branchDTO = new BranchDTO();
        long organizationId = 1L;
        when(branchService.createBranch(organizationId, branchDTO)).thenReturn(Collections.singletonList(branchDTO));

        ResponseEntity<List<BranchDTO>> response = branchController.createBranch(organizationId, branchDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(branchDTO, response.getBody().get(0));

        verify(branchService, times(1)).createBranch(organizationId, branchDTO);
    }

    @Test
    void testGetBranchById() {
        BranchDTO branchDTO = new BranchDTO();
        long branchId = 1L;
        when(branchService.getDetailOfBranchById(branchId)).thenReturn(branchDTO);

        ResponseEntity<BranchDTO> response = branchController.getBranchById(branchId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branchDTO, response.getBody());

        verify(branchService, times(1)).getDetailOfBranchById(branchId);
    }

    @Test
    void testUpdateBranch() {
        BranchDTO branchDTO = new BranchDTO();
        long branchId = 1L;
        when(branchService.updateBranch(branchId, branchDTO)).thenReturn(branchDTO);

        ResponseEntity<BranchDTO> response = branchController.updateBranch(branchId, branchDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branchDTO, response.getBody());

        verify(branchService, times(1)).updateBranch(branchId, branchDTO);
    }

    @Test
    void testGetAllBranchInformation() {
        BranchDTO branchDTO = new BranchDTO();
        List<BranchDTO> branchDTOList = Collections.singletonList(branchDTO);
        int page = 0;
        int size = 10;
        when(branchService.getAllBranchInformation(page, size)).thenReturn(branchDTOList);

        ResponseEntity<List<BranchDTO>> response = branchController.getAllBranchInformation(page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(branchDTO, response.getBody().get(0));

        verify(branchService, times(1)).getAllBranchInformation(page, size);
    }
}
