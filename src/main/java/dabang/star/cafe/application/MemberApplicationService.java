package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.DuplicatedException;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.infrastructure.MybatisMemberRepository;
import dabang.star.cafe.infrastructure.mapper.read.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberApplicationService {

    private final MybatisMemberRepository mybatisMemberRepository;
    private final MemberReadService memberReadService;
    private final EncryptService encryptService;

    @Transactional
    public Long join(Member member) {

        checkDuplicatedMember(member);
        member.setPassword(encryptService.encrypt(member.getPassword()));

        return mybatisMemberRepository.save(member);
    }

    private void checkDuplicatedMember(Member member) {

        if (memberReadService.exist(member.getEmail())) {
            throw new DuplicatedException("duplicated Email");
        }
    }
}
