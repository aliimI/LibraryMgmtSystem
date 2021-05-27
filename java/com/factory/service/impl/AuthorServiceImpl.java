package com.factory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.factory.entity.Author;
import com.factory.exception.NotFoundException;
import com.factory.repository.AuthorRepository;
import com.factory.service.AuthorService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Author with ID %d not found", id)));
    }

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with ID %d not found", id)));

        authorRepository.deleteById(author.getId());
    }

}
