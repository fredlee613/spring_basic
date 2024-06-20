package hello.core.member;

public class MemberServiceImpl implements MemberService {
    private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memoryMemberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memoryMemberRepository.findById(memberId);
    }
}
