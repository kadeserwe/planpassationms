package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.PlanpassationmsApp;
import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.domain.enumeration.DataType;
import sn.ssi.sigmap.repository.TableRowRepository;
import sn.ssi.sigmap.service.TableRowService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TableRowResource} REST controller.
 */
@SpringBootTest(classes = PlanpassationmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TableRowResourceIT {

    private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LABEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_NAME = "BBBBBBBBBB";

    private static final DataType DEFAULT_DATA_TYPE = DataType.INT;
    private static final DataType UPDATED_DATA_TYPE = DataType.MONEY;

    @Autowired
    private TableRowRepository tableRowRepository;

    @Autowired
    private TableRowService tableRowService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTableRowMockMvc;

    private TableRow tableRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableRow createEntity(EntityManager em) {
        TableRow tableRow = new TableRow()
            .tableName(DEFAULT_TABLE_NAME)
            .labelName(DEFAULT_LABEL_NAME)
            .columnName(DEFAULT_COLUMN_NAME)
            .dataType(DEFAULT_DATA_TYPE);
        return tableRow;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableRow createUpdatedEntity(EntityManager em) {
        TableRow tableRow = new TableRow()
            .tableName(UPDATED_TABLE_NAME)
            .labelName(UPDATED_LABEL_NAME)
            .columnName(UPDATED_COLUMN_NAME)
            .dataType(UPDATED_DATA_TYPE);
        return tableRow;
    }

    @BeforeEach
    public void initTest() {
        tableRow = createEntity(em);
    }

    @Test
    @Transactional
    public void createTableRow() throws Exception {
        int databaseSizeBeforeCreate = tableRowRepository.findAll().size();
        // Create the TableRow
        restTableRowMockMvc.perform(post("/api/table-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tableRow)))
            .andExpect(status().isCreated());

        // Validate the TableRow in the database
        List<TableRow> tableRowList = tableRowRepository.findAll();
        assertThat(tableRowList).hasSize(databaseSizeBeforeCreate + 1);
        TableRow testTableRow = tableRowList.get(tableRowList.size() - 1);
        assertThat(testTableRow.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
        assertThat(testTableRow.getLabelName()).isEqualTo(DEFAULT_LABEL_NAME);
        assertThat(testTableRow.getColumnName()).isEqualTo(DEFAULT_COLUMN_NAME);
        assertThat(testTableRow.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
    }

    @Test
    @Transactional
    public void createTableRowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tableRowRepository.findAll().size();

        // Create the TableRow with an existing ID
        tableRow.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableRowMockMvc.perform(post("/api/table-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tableRow)))
            .andExpect(status().isBadRequest());

        // Validate the TableRow in the database
        List<TableRow> tableRowList = tableRowRepository.findAll();
        assertThat(tableRowList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTableRows() throws Exception {
        // Initialize the database
        tableRowRepository.saveAndFlush(tableRow);

        // Get all the tableRowList
        restTableRowMockMvc.perform(get("/api/table-rows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].labelName").value(hasItem(DEFAULT_LABEL_NAME)))
            .andExpect(jsonPath("$.[*].columnName").value(hasItem(DEFAULT_COLUMN_NAME)))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTableRow() throws Exception {
        // Initialize the database
        tableRowRepository.saveAndFlush(tableRow);

        // Get the tableRow
        restTableRowMockMvc.perform(get("/api/table-rows/{id}", tableRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tableRow.getId().intValue()))
            .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
            .andExpect(jsonPath("$.labelName").value(DEFAULT_LABEL_NAME))
            .andExpect(jsonPath("$.columnName").value(DEFAULT_COLUMN_NAME))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTableRow() throws Exception {
        // Get the tableRow
        restTableRowMockMvc.perform(get("/api/table-rows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTableRow() throws Exception {
        // Initialize the database
        tableRowService.save(tableRow);

        int databaseSizeBeforeUpdate = tableRowRepository.findAll().size();

        // Update the tableRow
        TableRow updatedTableRow = tableRowRepository.findById(tableRow.getId()).get();
        // Disconnect from session so that the updates on updatedTableRow are not directly saved in db
        em.detach(updatedTableRow);
        updatedTableRow
            .tableName(UPDATED_TABLE_NAME)
            .labelName(UPDATED_LABEL_NAME)
            .columnName(UPDATED_COLUMN_NAME)
            .dataType(UPDATED_DATA_TYPE);

        restTableRowMockMvc.perform(put("/api/table-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTableRow)))
            .andExpect(status().isOk());

        // Validate the TableRow in the database
        List<TableRow> tableRowList = tableRowRepository.findAll();
        assertThat(tableRowList).hasSize(databaseSizeBeforeUpdate);
        TableRow testTableRow = tableRowList.get(tableRowList.size() - 1);
        assertThat(testTableRow.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testTableRow.getLabelName()).isEqualTo(UPDATED_LABEL_NAME);
        assertThat(testTableRow.getColumnName()).isEqualTo(UPDATED_COLUMN_NAME);
        assertThat(testTableRow.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTableRow() throws Exception {
        int databaseSizeBeforeUpdate = tableRowRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableRowMockMvc.perform(put("/api/table-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tableRow)))
            .andExpect(status().isBadRequest());

        // Validate the TableRow in the database
        List<TableRow> tableRowList = tableRowRepository.findAll();
        assertThat(tableRowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTableRow() throws Exception {
        // Initialize the database
        tableRowService.save(tableRow);

        int databaseSizeBeforeDelete = tableRowRepository.findAll().size();

        // Delete the tableRow
        restTableRowMockMvc.perform(delete("/api/table-rows/{id}", tableRow.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TableRow> tableRowList = tableRowRepository.findAll();
        assertThat(tableRowList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
