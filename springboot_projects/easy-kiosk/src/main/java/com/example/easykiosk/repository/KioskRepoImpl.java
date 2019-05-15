package com.example.easykiosk.repository;

import com.example.easykiosk.model.UserDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public class KioskRepoImpl implements KioskRepository{
    @Override
    public List<UserDetails> findAll() {
        return null;
    }

    @Override
    public List<UserDetails> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserDetails> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserDetails> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserDetails userDetails) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserDetails> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserDetails> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserDetails> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserDetails> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<UserDetails> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Query(value = "SELECT * FROM UserDetails", nativeQuery = true)
    public UserDetails getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserDetails> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserDetails> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserDetails> boolean exists(Example<S> example) {
        return false;
    }

    @Query(value = "SELECT * FROM UserDetails u WHERE u.username= ?1 ", nativeQuery = true)
    public UserDetails findByUsername(String username) {
        return null;
    }
}
