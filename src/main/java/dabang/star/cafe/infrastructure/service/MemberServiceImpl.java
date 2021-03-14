package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.DuplicatedException;
import dabang.star.cafe.api.exception.MemberNotFoundException;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.EncryptService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    @Override
    public MemberData join(Member member) {

        checkDuplicatedMember(member);
        member.setPassword(encryptService.encrypt(member.getPassword()));

        memberRepository.save(member);

        return new MemberData(member);
    }

    @Override
    public void checkDuplicatedMember(Member member) {

        if (memberRepository.isExist(member.getEmail())) {
            throw new DuplicatedException("duplicated Email");
        }
    }

    @Override
    public void update(Member member) {
        member.setPassword(encryptService.encrypt(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public MemberData loadById(long id) {
        return memberRepository.findMemberById(id).orElseThrow(
                () -> new MemberNotFoundException("member not found")
        );
    }

    @Override
    public void secession(long id) {
        memberRepository.deleteById(id);
    }
}
