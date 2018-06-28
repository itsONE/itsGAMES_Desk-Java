package utfpr.itsone.app;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JasperReportExample {
    public JasperReportExample(){
    }

    public static void generate(String layout, ResultSet rs, String file) throws JRException, SQLException, ClassNotFoundException{
        //gerando o jasper design
        JasperDesign drawing = JRXmlLoader.load(file);

        //compila o relatório
        JasperReport report = JasperCompileManager.compileReport(drawing);

        //implementação da interface JRDataSource para DataSource ResultSet
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

        //executa o relatório
        Map params = new HashMap();
        params.put("HEADER", "Relatório de " + layout);
        params.put("FOOTER", "Final do Relatório - 2018 - UTFPR");
        JasperPrint print = JasperFillManager.fillReport(report, params, jrRS);

        //exibe o resultado
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.show();
    }
}
