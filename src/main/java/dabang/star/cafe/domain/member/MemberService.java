package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.response.member.MemberData;

public interface MemberService {

    MemberData join(Member member);

    void checkDuplicatedMember(Member member);

    void update(Member member);

    MemberData findById(Long id);

    public void secession(Long id);
}
