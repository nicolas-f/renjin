package org.renjin.gcc.peephole;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Optimizes generated bytecode by replacing groups of instructions with more space efficient representations.
 *
 * <p>These sorts of optimizations don't appear to have much impact on the performance of compiled
 * methods, but it can drastically reduce the size of the generated bytecode.</p>
 */
public class PeepholeOptimizer {

  public static PeepholeOptimizer INSTANCE = new PeepholeOptimizer();

  private List<PeepholeOptimization> optimizations = Lists.newArrayList();

  public PeepholeOptimizer() {
    optimizations.add(new StoreLoad());
  //  optimizations.add(new LoadLoad());
    optimizations.add(new IntegerIncrement());
  }

  public void optimize(MethodNode methodNode) {

    System.out.println("BEFORE " + methodNode.name);
    methodNode.accept(new TraceMethodVisitor(new Textifier()));

    NodeIt it = new NodeIt(methodNode.instructions, findJumpTargets(methodNode));
    do {
      boolean changing;
      do {
        changing = false;
        for (PeepholeOptimization optimization : optimizations) {
          if (optimization.apply(it)) {
            changing = true;
          }
        }
      } while (changing);
    } while (it.next());
  }

  private Set<Label> findJumpTargets(MethodNode methodNode) {
    Set<Label> targets = Sets.newHashSet();
    ListIterator<AbstractInsnNode> it = methodNode.instructions.iterator();
    while(it.hasNext()) {
      AbstractInsnNode node = it.next();
      if(node instanceof JumpInsnNode) {
        JumpInsnNode jumpNode = (JumpInsnNode) node;
        targets.add(jumpNode.label.getLabel());
      }
    }
    return targets;
  }
}