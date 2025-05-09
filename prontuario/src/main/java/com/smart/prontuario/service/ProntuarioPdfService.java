package com.smart.prontuario.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.smart.prontuario.model.Prontuario;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class ProntuarioPdfService {

    public byte[] generatePdf(Prontuario prontuario) throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Adiciona o cabeçalho
            addHeader(document, prontuario);

            // Adiciona os dados do paciente
            addPacienteInfo(document, prontuario);

            // Adiciona os dados do prontuário
            addProntuarioInfo(document, prontuario);

            // Adiciona o rodapé
            addFooter(document);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao gerar PDF do prontuário", e);
        }

        return out.toByteArray();
    }

    private void addHeader(Document document, Prontuario prontuario) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        Paragraph title = new Paragraph("PRONTUÁRIO MÉDICO", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        Paragraph subtitle = new Paragraph("Clínica Estética", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingAfter(30);
        document.add(subtitle);
    }

    private void addPacienteInfo(Document document, Prontuario prontuario) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        Paragraph sectionTitle = new Paragraph("DADOS DO PACIENTE", sectionFont);
        sectionTitle.setSpacingBefore(20);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(20);

        addTableRow(table, "Nome:", prontuario.getPaciente().getNome(), normalFont);
        addTableRow(table, "CPF:", prontuario.getPaciente().getCpf(), normalFont);
        addTableRow(table, "Data de Nascimento:", prontuario.getPaciente().getDataNascimento().toString(), normalFont);
        addTableRow(table, "Telefone:", prontuario.getPaciente().getTelefone(), normalFont);
        addTableRow(table, "Email:", prontuario.getPaciente().getEmail(), normalFont);

        document.add(table);
    }

    private void addProntuarioInfo(Document document, Prontuario prontuario) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        Paragraph sectionTitle = new Paragraph("INFORMAÇÕES DO PRONTUÁRIO", sectionFont);
        sectionTitle.setSpacingBefore(20);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        addTableRow(table, "Data da Consulta:", prontuario.getDataConsulta().format(formatter), normalFont);
        addTableRow(table, "Médico:", prontuario.getNomeMedico(), normalFont);
        addTableRow(table, "CRM:", prontuario.getCrmMedico(), normalFont);
        addTableRow(table, "Queixa Principal:", prontuario.getQueixaPrincipal(), normalFont);
        addTableRow(table, "Sintomas:", prontuario.getSintomas(), normalFont);
        addTableRow(table, "Diagnóstico:", prontuario.getDiagnostico(), normalFont);
        addTableRow(table, "Prescrição:", prontuario.getPrescricao(), normalFont);
        addTableRow(table, "Histórico de Procedimentos:", prontuario.getHistoricoProcedimentosEsteticos(), normalFont);
        addTableRow(table, "Medicamentos/Suplementos:", prontuario.getUsoMedicamentosSuplementos(), normalFont);
        addTableRow(table, "Alergias:", prontuario.getAlergias(), normalFont);
        addTableRow(table, "Condições Pré-existentes:", prontuario.getCondicoesSaudePreExistentes(), normalFont);
        addTableRow(table, "Expectativas:", prontuario.getExpectativasPreferencias(), normalFont);
        addTableRow(table, "Observações:", prontuario.getObservacoes(), normalFont);

        document.add(table);
    }

    private void addFooter(Document document) throws DocumentException {
        Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
        Paragraph footer = new Paragraph("Documento gerado em: " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), footerFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(50);
        document.add(footer);
    }

    private void addTableRow(PdfPTable table, String label, String value, Font font) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, font));
        labelCell.setBorderWidth(0.5f);
        labelCell.setPadding(5);
        labelCell.setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell valueCell = new PdfPCell(new Phrase(value != null ? value : "", font));
        valueCell.setBorderWidth(0.5f);
        valueCell.setPadding(5);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }
} 