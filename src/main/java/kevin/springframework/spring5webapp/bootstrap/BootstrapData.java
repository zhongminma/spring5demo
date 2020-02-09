package kevin.springframework.spring5webapp.bootstrap;


import kevin.springframework.spring5webapp.domain.Book;
import kevin.springframework.spring5webapp.domain.Publisher;
import kevin.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import kevin.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    public BootstrapData(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start bootstrap---------------------");
        Book book1 = new Book("First Blood", "800-100");
        Book book2 = new Book();
        book2.setTitle("Last One");
        book2.setIsbn("USA-0000");
        bookRepository.save(book1);
        bookRepository.save(book2);
        System.out.println("Book instance count is: " + bookRepository.count());

        Publisher publisher = new Publisher();
        publisher.setName("Banns Nobles");
        publisher.setAddressLine1("100 Main St");
        publisher.setCity("NYC");
        publisher.setState("NY");
        publisher.setZipCode("10004");
        publisherRepository.save(publisher);
        System.out.println("Publisher instance count is: " + publisherRepository.count());

        // Mapping staff
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        publisherRepository.save(publisher);
        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);
        publisherRepository.save(publisher);
        System.out.println("books count: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());


    }
}
