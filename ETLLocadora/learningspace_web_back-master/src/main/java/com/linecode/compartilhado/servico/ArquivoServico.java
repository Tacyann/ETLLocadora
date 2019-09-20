package com.linecode.compartilhado.servico;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linecode.compartilhado.error.ErroAplicacao;

@Service
public class ArquivoServico {
	
	@Autowired
	private Environment env;
	
	/**
	 * Salva um arquivo em um caminho informado.
	 * @param blob - bytes do arquivo {@link byte[]}
	 * @param nomeArquivo- caminho absoluto do arquivo {@link String}
	 * @throws {@link ErroAplicacao} para qualquer erro de IO do arquivo.
	 */
	public void salvarArquivo(byte[] blob, String nomeArquivo) {
		
		StringBuilder caminhoFinal = new StringBuilder();
		caminhoFinal.append(env.getProperty("pasta.upload"));
		caminhoFinal.append(File.separator);
		caminhoFinal.append(nomeArquivo);
		
		String caminhoFinalStr = caminhoFinal.toString();
		File arquivoFinal = new File(caminhoFinalStr);
		
		if (arquivoFinal.exists()) {
			throw new ErroAplicacao("O arquivo já existe: ".concat(caminhoFinalStr), new Exception());
		}
		
		try {
			
			criarDiretorios(caminhoFinalStr);
			arquivoFinal.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(arquivoFinal);
			fos.write(blob);
			fos.close();
			
		} catch (IOException e) {
			throw new ErroAplicacao("Erro ao manipular arquivo ".concat(caminhoFinalStr), e);
		}	
	}
	
	/**
	 * Delete um arquivo pelo caminho absoluto.
	 * @param nomeArquivo- caminho absoluto do arquivo {@link String}
	 * @throws {@link ErroAplicacao} para qualquer erro de IO do arquivo.
	 */
	public void deletarArquivo(String caminhoArquivo) {
		
		caminhoArquivo = env.getProperty("pasta.upload").concat(File.separator).concat(caminhoArquivo);
		
		try {
			Files.deleteIfExists(Paths.get(caminhoArquivo));
		} catch (IOException e) {
			throw new ErroAplicacao("Erro ao deletar arquivo: ".concat(caminhoArquivo), e);
		}
	}
	
	/**
	 * A partir do caminho absoluto de um arquivo
	 * cria a estrutura de diretório, caso não exista
	 * 
	 * @param caminho absoluto do arquivo
	 * @throws IOException
	 */
	private void criarDiretorios(String caminhoArquivo) throws IOException {
		
		String[] diretorios = caminhoArquivo.split(File.separator);
		StringBuilder diretorioSemArquivo = new StringBuilder();
		
		for (int i = 0; i < diretorios.length - 1; i++) {
			diretorioSemArquivo.append(diretorios[i]);
			diretorioSemArquivo.append(File.separator);
		}
		
		Path diretorio = Paths.get(diretorioSemArquivo.toString());
		
		if (!Files.exists(diretorio)) {
			Files.createDirectories(Paths.get(diretorioSemArquivo.toString()));
		}
	}
	
	/**
	 * Deleta um diretório informado com seus arquivos.
	 * @param diretorio a ser deletado
	 * @throws {@link ErroAplicacao} caso não consiga deletar o diretório
	 */
	public void deletarPasta(String nomePasta) {
		try {
			
			StringBuilder diretorio = new StringBuilder();
			diretorio.append(env.getProperty("pasta.upload"));
			diretorio.append(File.separator);
			diretorio.append(nomePasta);
			
			String diretorioStr = diretorio.toString();
			
			for (File arquivo : new File(diretorioStr).listFiles()) {
				arquivo.delete();
			}
			
			Files.deleteIfExists(Paths.get(diretorioStr));
		} catch (IOException e) {
			throw new ErroAplicacao("Erro ao deletar o diretório ", e);
		}
	}
	
	/**
	 * Retorna a extenssao de um arquivo
	 * @param arquivo {@link MultipartFile}
	 * @return extensao do arquivo {@link String}
	 */
	public String getExtensao(MultipartFile arquivo) {
		String[] flags = arquivo.getOriginalFilename().split("\\.");
		return flags[flags.length-1];
	}
	
	/**
	 * Retorna o arquivo no formate de String base64
	 * @param caminho do arquivo.
	 * @return string bas64 do arquivo.
	 */
	public String getBase64(String caminhoArquivo) {
		
		StringBuilder caminhoFinal = new StringBuilder();
		caminhoFinal.append(env.getProperty("pasta.upload"));
		caminhoFinal.append(File.separator);
		caminhoFinal.append(caminhoArquivo);
		
		File file = new File(caminhoFinal.toString());
		
		try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
			
			byte[] bytes = new byte[(int)file.length()];
	        fileInputStreamReader.read(bytes);
	        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
	         
		} catch (IOException e) {
			throw new ErroAplicacao("Erro ao carregar foto do usuário", e);
		}
     }
	
	/**
	 * Retorna o arquivo apartir do caminho
	 * 
	 * @param caminho do arquivo
	 * @return uma instencao de {@link Path}
	 */
	public Path getArquivo(String caminhoArquivo) {		
		return Paths.get(env.getProperty("pasta.upload").concat(File.separator).concat(caminhoArquivo));
	}
}
