import com.mandacarubroker.service.StockService;
import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.stock.StockRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStockById() {
        // Arrange
        String stockId = "123";
        Stock stock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // Act
        Optional<Stock> result = stockService.getStockById(stockId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(stock, result.get());
        verify(stockRepository, times(1)).findById(stockId);
    }
    
    @Test
    void createAndValidateStock() {
        // Arrange
        RequestStockDTO requestStockDTO = new RequestStockDTO();
        Stock stock = new Stock(requestStockDTO);
        when(stockRepository.save(stock)).thenReturn(stock);

        // Act
        Stock result = stockService.createAndValidadeStock(requestStockDTO);

        // Assert
        assertNotNull(result);
        verify(stockRepository, times(1)).save(stock);
    }
}
