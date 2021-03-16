package sn.ssi.sigmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import sn.ssi.sigmap.dao.TableDao;
import sn.ssi.sigmap.domain.Realisation;
import sn.ssi.sigmap.domain.SigRealisation;
import sn.ssi.sigmap.domain.TableDeTransaction;
import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.repository.SigRealisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ssi.sigmap.service.dto.SigRealisationDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SigRealisation}.
 */
@Service
@Transactional
public class SigRealisationService {

    private final Logger log = LoggerFactory.getLogger(SigRealisationService.class);

    private final SigRealisationRepository sigRealisationRepository;

    @Autowired
    private TableService tableService;

    @Autowired
    private TableDao tableDao;

    public SigRealisationService(SigRealisationRepository sigRealisationRepository) {
        this.sigRealisationRepository = sigRealisationRepository;
    }

    /**
     * Save a sigRealisation.
     *
     * @param sigRealisation the entity to save.
     * @return the persisted entity.
     */
    public SigRealisation save(SigRealisation sigRealisation) {
        log.debug("Request to save SigRealisation : {}", sigRealisation);
        return sigRealisationRepository.save(sigRealisation);
    }

    /**
     * Get all the sigRealisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SigRealisation> findAll(Pageable pageable) {
        log.debug("Request to get all SigRealisations");
        return sigRealisationRepository.findAll(pageable);
    }


    /**
     * Get one sigRealisation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SigRealisation> findOne(Long id) {
        log.debug("Request to get SigRealisation : {}", id);
        return sigRealisationRepository.findById(id);
    }

    /**
     * Delete the sigRealisation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SigRealisation : {}", id);
        sigRealisationRepository.deleteById(id);
    }

    public SigRealisationDTO getFields() {
        List<TableRow> extendedFields = tableDao.findColumnsByTableName("sig_realisation");
        List<Map<String, Object>> fields = tableService.rowJsonWrapper(extendedFields);
        return new SigRealisationDTO(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            fields);
    }

    public SigRealisationDTO getFields(Long masterId) {
        List<TableDeTransaction> extendedFields = tableDao.findColumnByTableNameAndMasterId("sig_realisation", masterId);
        Optional<SigRealisation> data = sigRealisationRepository.findById(masterId);
        List<Map<String, Object>> fields = tableService.jsonWrapper(extendedFields);
        SigRealisation sigRealisation = data.get();

        return new SigRealisationDTO(
            sigRealisation.getId(),
            sigRealisation.getLibelle(),
            sigRealisation.getDateAttribution(),
            sigRealisation.getDelaiexecution(),
            sigRealisation.getObjet(),
            sigRealisation.getMontant(),
            sigRealisation.getExamenDncmp(),
            sigRealisation.getExamenPtf(),
            sigRealisation.getChapitreImputation(),
            sigRealisation.getAutorisationEngagement(),
            sigRealisation.getDateReceptionDossier(),
            sigRealisation.getDateNonObjection(),
            sigRealisation.getDateAutorisation(),
            sigRealisation.getDateRecepNonObjection(),
            sigRealisation.getDateRecepDossCorrige(),
            sigRealisation.getDatePubParPrmp(),
            sigRealisation.getDateOuverturePlis(),
            sigRealisation.getDateRecepNonObjectOcmp(),
            sigRealisation.getDateRecepRapportEva(),
            sigRealisation.getDateRecepNonObjectPtf(),
            sigRealisation.getDateExamenJuridique(),
            sigRealisation.getDateNotifContrat(),
            sigRealisation.getDateApprobationContrat(),
            sigRealisation.getPlanPassation(),
            fields);
    }

    public SigRealisationDTO save1(SigRealisationDTO  params) throws ParseException {
        List<TableDeTransaction> data = tableService.jsonUnwrapper(params.getExtended());
        SigRealisation sigRealisation = params.getSigRealisation();
        sigRealisation = sigRealisationRepository.save(sigRealisation);
        data = tableDao.save("sig_realisation", sigRealisation.getId(), data);
        List<Map<String, Object>> extendData = tableService.jsonWrapper(data);
        return new SigRealisationDTO(
            sigRealisation.getId(),
            sigRealisation.getLibelle(),
            sigRealisation.getDateAttribution(),
            sigRealisation.getDelaiexecution(),
            sigRealisation.getObjet(),
            sigRealisation.getMontant(),
            sigRealisation.getExamenDncmp(),
            sigRealisation.getExamenPtf(),
            sigRealisation.getChapitreImputation(),
            sigRealisation.getAutorisationEngagement(),
            sigRealisation.getDateReceptionDossier(),
            sigRealisation.getDateNonObjection(),
            sigRealisation.getDateAutorisation(),
            sigRealisation.getDateRecepNonObjection(),
            sigRealisation.getDateRecepDossCorrige(),
            sigRealisation.getDatePubParPrmp(),
            sigRealisation.getDateOuverturePlis(),
            sigRealisation.getDateRecepNonObjectOcmp(),
            sigRealisation.getDateRecepRapportEva(),
            sigRealisation.getDateRecepNonObjectPtf(),
            sigRealisation.getDateExamenJuridique(),
            sigRealisation.getDateNotifContrat(),
            sigRealisation.getDateApprobationContrat(),
            sigRealisation.getPlanPassation(),
            extendData);
    }
}
