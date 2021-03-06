package com.prado.moneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prado.moneyapi.model.Lancamento;
import com.prado.moneyapi.model.Pessoa;
import com.prado.moneyapi.repository.LancamentoRepository;
import com.prado.moneyapi.repository.PessoaRepository;
import com.prado.moneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
			
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
