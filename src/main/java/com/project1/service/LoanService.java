package com.project1.service;

import com.project1.dto.LoanRequest;
import com.project1.dto.LoanResponse;
import com.project1.entity.Loan;
import com.project1.entity.Member;
import com.project1.repository.LoanRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LoanService {
    LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }

    //Skal gøres, så alle loans kan findes på et memberid
    public List<LoanResponse> getAllLoansOnMember(LoanRequest username){
        List<Loan> loans = loanRepository.findAll();
        loans.stream().map(n -> username.getUsername());
        return LoanResponse.getLoansFromEntity(loans);
    }

    //Denne metode skal gerne kunne bruges til at tilføje individuelt dates
    public LoanResponse addDate(LoanRequest body, Member memberUsername){
        Loan newCheckoutDate = loanRepository.save(new Loan(body));
        newCheckoutDate.setUsername(memberUsername);
        return new LoanResponse(newCheckoutDate);
    }

    public LoanResponse editLoan(LoanRequest body, int id){
        return null;
    }

    public void deleteLoan(int loanId){
        loanRepository.deleteById(loanId);
    }
}