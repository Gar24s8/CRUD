//package services;
//
//import models.Employee;
//import models.Office;
//import models.Task;
//import utils.HibernateSessionFactoryUtil;
//
//public class Demo {
//
//    public void demonstrate() {
//        CRUDService service = new CRUDService();
//
//        System.out.println("added first employee, office and task");
//        Office office1 = new Office("Dalnaya", "Dalnaya 39");
//        Employee employee1 = new Employee("Elena", "boss", 500000);
//        Task task1 = new Task("rate code", "04.07.2023");
//        office1.addEmployee(employee1);
//        employee1.addTask(task1);
//        service.createOffice(office1);
//        System.out.println();
//
//        System.out.println("added second employee, office and task");
//        Office office2 = new Office("Lukjanenko", "Lukjanenko 101");
//        Employee employee2 = new Employee("Igor", "engineer", 100000);
//        Task task2 = new Task("finish app", "04.07.2023");
//        office2.addEmployee(employee2);
//        employee2.addTask(task2);
//        service.createOffice(office2);
//        System.out.println();
//
//        System.out.println("added third task for both");
//        Task task3 = new Task("chill", "04.07.2023");
//        task3.addEmployee(employee1);
//        task3.addEmployee(employee2);
//        service.createTask(task3);
//        System.out.println();
//
//        System.out.println("show all tasks");
//        service.findAllTasks();
//        System.out.println();
//
//        System.out.println("delete task3");
//        task3.removeEmployee(employee2);
//        task3.removeEmployee(employee1);
//        service.updateTask(task3);
//        service.deleteTask(task3);
//        System.out.println();
//
//        System.out.println("show tasks after deleting task3");
//        service.findAllTasks();
//        System.out.println();
//
//        HibernateSessionFactoryUtil.getSessionFactory().close();
//    }
//}
