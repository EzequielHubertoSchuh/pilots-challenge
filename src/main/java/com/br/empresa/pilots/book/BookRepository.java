package com.br.empresa.pilots.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);

    //public List<Book> findByOrderByNameAsc();

    //public List<Book> findByOrderByAuthorAscPublicationDateDesc();
}
