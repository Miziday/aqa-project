package infostructure;

import org.testng.*;
import org.testng.xml.XmlSuite;
import java.util.List;

// Пример реализации интерфейса IReporter для создания своего отчета (в данном кейсе просто лог в консоль)

public class MyCustomReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {

        // Счетчики
        int totalPass = 0, totalFail = 0, totalSkip = 0;

        // 1. Перебираем все сьюты (наборы тестов)
        for (ISuite suite : suites) {
            // 2. Из каждого сьюта получаем результаты методов
            for (ISuiteResult suiteResult : suite.getResults().values()) {
                ITestContext context = suiteResult.getTestContext();

                // 3. Суммируем результаты
                totalPass += context.getPassedTests().size();
                totalFail += context.getFailedTests().size();
                totalSkip += context.getSkippedTests().size();
            }
        }

        // 4. Выводим красивую статистику
        System.out.println("\n=========================================");
        System.out.println("         ГЕНЕРАЦИЯ ИТОГОВОГО ОТЧЕТА");
        System.out.println("=========================================");
        System.out.println("✅ PASSED:  " + totalPass);
        System.out.println("❌ FAILED:  " + totalFail);
        System.out.println("⚠️ SKIPPED: " + totalSkip);
        System.out.println("=========================================");
    }
}