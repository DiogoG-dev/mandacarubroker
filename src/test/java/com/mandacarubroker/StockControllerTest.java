import com.mandacarubroker.controller.StockController;
import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getStockByIdShouldReturnStock() {
        // Arrange
        String stockId = "1";
        Stock mockStock = new Stock();
        when(stockService.getStockById(stockId)).thenReturn(Optional.of(mockStock));

        // Act
        Stock result = stockController.getStockById(stockId);

        // Assert
        assertNotNull(result);
    }
    
    @Test
    void createStock_ShouldReturnCreatedStock() {
        // Arrange
        RequestStockDTO requestStockDTO = new RequestStockDTO();
        Stock createdStock = new Stock();
        when(stockService.createStock(requestStockDTO)).thenReturn(createdStock);

        // Act
        ResponseEntity<Stock> result = stockController.createStock(requestStockDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdStock, result.getBody());
    }
