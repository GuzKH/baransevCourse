package fourthTask.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import fourthTask.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);

        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

//        int count = Integer.parseInt(args[0]);
//        File file = new File(args[1]);
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(" . ").getAbsolutePath());

        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n",
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getAddress(),
                    contact.getEmail(),
                    contact.getAllPhones()));
        }
        writer.close();
    }

    private List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("First name: test %s", i))
                    .withLastName(String.format(" Last name: test %s", i))
                    .withAddress(String.format(" Address: test %s", i))
                    .withEmail(String.format(" Email: test@%s", i))
                    .withAllPhones(String.format(" Phone(s): 981111111%s", i)));
        }
        return contacts;
    }
}
