package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Book;
import com.example.spring.data.model.Member;
import com.example.spring.data.model.MemberHired;
import com.example.spring.data.repository.BookRepo;
import com.example.spring.data.repository.MemberHiredRepo;
import com.example.spring.data.repository.MemberRepo;
import com.example.spring.data.service.BookService;
import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepo memberRepo;
    @Autowired
    MemberHiredRepo memberHiredRepo;
    @Autowired
    BookRepo bookRepo;

    public static BookService bookService;
    @Override
    public Member findMember(String idCard) {
        return memberRepo.findByIdCard(idCard);
    }

    @Override
    public List<Member> findMemberNameLike(String name) {
        return memberRepo.findByMemberNameLike("%"+name+"%");
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Member> createMember(List<Member> members) {
        return memberRepo.saveAll(members);
    }

    @Override
    public Member updateMember(Member member) {
        return memberRepo.save(member);
    }

    @Override
    public List<MemberHired> findMemberHiredBook(String idCard) {
        return memberHiredRepo.findByMemberIdCard(idCard);
    }

    @Override
    @Transactional
    public List<MemberHired> saveAll(List<MemberHired> memberHiredList) {
        memberHiredList.stream().forEach(item -> {
            Book book = bookRepo.findById(item.getBookId()).orElseThrow(()->new NullPointerException("No book in database"));
            item.setBookName(book.getBookName());
            Long total = book.getQuantity();
            book.setQuantity(total - item.getQuantity());
            bookRepo.save(book);
        });
        return memberHiredRepo.saveAll(memberHiredList);
    }

    @Override
    public List<MemberHired> scheduleUpdateAll() {
        List<MemberHired> memberHiredList = this.findAllMemberHired();
        memberHiredList.stream().forEach(item -> {
            if (item.getEndTimeHired() != null) {
               Instant endTime = item.getEndTimeHired();
               Instant scheduleTime = Instant.now();
               System.out.println("So sanh time: " +endTime+ "   " +scheduleTime);
               boolean check = endTime.isBefore(scheduleTime);
               if (check) {
                   Instant newEndTime = endTime.atZone(ZoneOffset.UTC).plusDays(1).toInstant();
                   Long forfeit = item.getUnitPrice() + item.getForfeit();
                   System.out.println("Endtime new: " +newEndTime);
                   item.setForfeit(forfeit);
                   item.setEndTimeHired(newEndTime);
               }
            }
        });
        return memberHiredRepo.saveAll(memberHiredList);
    }

    @Override
    public List<MemberHired> findAllMemberHired() {
        return memberHiredRepo.findAll();
    }

    @Override
    public MemberHired requestHireBook(MemberHired memberHired) {
        return memberHiredRepo.save(memberHired);
    }

    @Override
    public MemberHired approved(MemberHired memberHired) {

        return memberHiredRepo.save(memberHired);
    }

    @Override
    public MemberHired findRecordById(Long id) {
        return memberHiredRepo.findById(id).orElseThrow(()-> new NullPointerException("Record this id is not exist"));
    }

    @Override
    public void giveBackBook(Long id) {
       memberHiredRepo.deleteById(id);
    }
}
