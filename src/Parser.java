import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Arrays;

public class Parser {
  private Lexer scanner;
  private Symbol lookahead;
  public boolean verbose;
  public boolean tree;
  public boolean active_tree;

  private static String PROGRAM;
  private static String VARIABLES;
  private static String VARIABLES_EPSILON;
  private static String VARLIST;
  private static String VARLISTEND;
  private static String VARLISTEND_EPSILON;
  private static String CODE;
  private static String CODE_EPSILON;
  private static String INSTRUCTION_ASSIGN;
  private static String INSTRUCTION_IF;
  private static String INSTRUCTION_WHILE;
  private static String INSTRUCTION_FOR;
  private static String INSTRUCTION_PRINT;
  private static String INSTRUCTION_READ;
  private static String ASSIGN;
  private static String EXPRARITH;
  private static String HPPROD;
  private static String HPEXPR;
  private static String HPEXPR_EPSILON;
  private static String LPEXPR;
  private static String LPEXPR_EPSILON;
  private static String SIMPLEEXPR_VARNAME;
  private static String SIMPLEEXPR_NUMBER;
  private static String SIMPLEEXPR_PAREN;
  private static String SIMPLEEXPR_MINUS;
  private static String LPOP_PLUS;
  private static String LPOP_MINUS;
  private static String HPOP_TIMES;
  private static String HPOP_DIVIDE;
  private static String IF;
  private static String IFELSE;
  private static String IFELSE_EPSILON;
  private static String COND;
  private static String PCOND;
  private static String HPCOND_AND;
  private static String HPCOND_EPSILON;
  private static String LPCOND_OR;
  private static String LPCOND_EPSILON;
  private static String SIMPLECOND_NOT;
  private static String SIMPLECOND_COMP;
  private static String COMP_EQ;
  private static String COMP_GEQ;
  private static String COMP_GT;
  private static String COMP_LEQ;
  private static String COMP_LT;
  private static String COMP_NEQ;
  private static String WHILE;
  private static String FOR;
  private static String PRINT;
  private static String READ;
  private static String EXPLIST;
  private static String EXPLISTEND;
  private static String EXPLISTEND_EPSILON;

  public Parser(BufferedReader filePath, boolean v, boolean t) throws IOException {
    this.scanner = new Lexer(filePath);
    this.lookahead = scanner.yylex();
    this.verbose = v;
    this.tree = t;

    if (verbose) {
      this.PROGRAM = "PROGRAM ";
      this.VARIABLES = "VARIABLES ";
      this.VARIABLES_EPSILON = "VARIABLES_EPSILON ";
      this.VARLIST = "VARLIST ";
      this.VARLISTEND = "VARLISTEND ";
      this.VARLISTEND_EPSILON = "VARLISTEND_EPSILON ";
      this.CODE = "CODE ";
      this.CODE_EPSILON = "CODE_EPSILON";
      this.INSTRUCTION_ASSIGN = "INSTRUCTION_ASSIGN ";
      this.INSTRUCTION_IF = "INSTRUCTION_IF ";
      this.INSTRUCTION_WHILE = "INSTRUCTION_WHILE ";
      this.INSTRUCTION_FOR = "INSTRUCTION_FOR ";
      this.INSTRUCTION_PRINT = "INSTRUCTION_PRINT ";
      this.INSTRUCTION_READ = "INSTRUCTION_READ ";
      this.ASSIGN = "ASSIGN ";
      this.EXPRARITH = "EXPRARITH ";
      this.HPPROD = "HPPROD ";
      this.HPEXPR = "HPEXPR ";
      this.HPEXPR_EPSILON = "HPEXPR_EPSILON ";
      this.LPEXPR = "LPEXPR ";
      this.LPEXPR_EPSILON = "LPEXPR_EPSILON ";
      this.SIMPLEEXPR_VARNAME = "SIMPLEEXPR_VARNAME ";
      this.SIMPLEEXPR_NUMBER = "SIMPLEEXPR_NUMBER ";
      this.SIMPLEEXPR_PAREN = "SIMPLEEXPR_PAREN ";
      this.SIMPLEEXPR_MINUS = "SIMPLEEXPR_MINUS ";
      this.LPOP_PLUS = "LPOP_PLUS ";
      this.LPOP_MINUS = "LPOP_MINUS ";
      this.HPOP_TIMES = "HPOP_TIMES ";
      this.HPOP_DIVIDE = "HPOP_DIVIDE ";
      this.IF = "IF ";
      this.IFELSE = "IFELSE ";
      this.IFELSE_EPSILON = "IFELSE_EPSILON ";
      this.COND = "COND ";
      this.PCOND = "PCOND ";
      this.HPCOND_AND = "HPCOND_AND ";
      this.HPCOND_EPSILON = "HPCOND_EPSILON ";
      this.LPCOND_OR = "LPCOND_OR ";
      this.LPCOND_EPSILON = "LPCOND_EPSILON ";
      this.SIMPLECOND_NOT = "SIMPLECOND_NOT ";
      this.SIMPLECOND_COMP = "SIMPLECOND_COMP ";
      this.COMP_EQ = "COMP_EQ ";
      this.COMP_GEQ = "COMP_GEQ ";
      this.COMP_GT = "COMP_GT ";
      this.COMP_LEQ = "COMP_LEQ ";
      this.COMP_LT = "COMP_LT ";
      this.COMP_NEQ = "COMP_NEQ ";
      this.WHILE = "WHILE ";
      this.FOR = "FOR ";
      this.PRINT = "PRINT ";
      this.READ = "READ ";
      this.EXPLIST = "EXPLIST ";
      this.EXPLISTEND = "EXPLISTEND ";
      this.EXPLISTEND_EPSILON = "EXPLISTEND_EPSILON ";
    } else {
      this.PROGRAM = "1 ";
      this.VARIABLES = "2 ";
      this.VARIABLES_EPSILON = "3 ";
      this.VARLIST = "4 ";
      this.VARLISTEND = "5 ";
      this.VARLISTEND_EPSILON = "6 ";
      this.CODE = "7 ";
      this.CODE_EPSILON = "8 ";
      this.INSTRUCTION_ASSIGN = "9 ";
      this.INSTRUCTION_IF = "10 ";
      this.INSTRUCTION_WHILE = "11 ";
      this.INSTRUCTION_FOR = "12 ";
      this.INSTRUCTION_PRINT = "13 ";
      this.INSTRUCTION_READ = "14 ";
      this.ASSIGN = "15 ";
      this.EXPRARITH = "16 ";
      this.HPPROD = "17 ";
      this.HPEXPR = "18 ";
      this.HPEXPR_EPSILON = "19 ";
      this.LPEXPR = "20 ";
      this.LPEXPR_EPSILON = "21 ";
      this.SIMPLEEXPR_VARNAME = "22 ";
      this.SIMPLEEXPR_NUMBER = "23 ";
      this.SIMPLEEXPR_PAREN = "24 ";
      this.SIMPLEEXPR_MINUS = "25 ";
      this.LPOP_PLUS = "26 ";
      this.LPOP_MINUS = "27 ";
      this.HPOP_TIMES = "28 ";
      this.HPOP_DIVIDE = "29 ";
      this.IF = "30 ";
      this.IFELSE = "31 ";
      this.IFELSE_EPSILON = "32 ";
      this.COND = "33 ";
      this.PCOND = "34 ";
      this.HPCOND_AND = "35 ";
      this.HPCOND_EPSILON = "36 ";
      this.LPCOND_OR = "37 ";
      this.LPCOND_EPSILON = "38 ";
      this.SIMPLECOND_NOT = "39 ";
      this.SIMPLECOND_COMP = "40 ";
      this.COMP_EQ = "41 ";
      this.COMP_GEQ = "42 ";
      this.COMP_GT = "43 ";
      this.COMP_LEQ = "44 ";
      this.COMP_LT = "45 ";
      this.COMP_NEQ = "46 ";
      this.WHILE = "47 ";
      this.FOR = "48 ";
      this.PRINT = "49 ";
      this.READ = "50 ";
      this.EXPLIST = "51 ";
      this.EXPLISTEND = "52 ";
      this.EXPLISTEND_EPSILON = "53 ";
    }
  }

  private void nextToken() throws IOException {
    this.lookahead = scanner.yylex();
  }

  private ParseTree compareToken(LexicalUnit token) throws IOException {
    if (!(lookahead.getType().equals(token))){
      throw new Error("\nError at line " + lookahead.getLine() + ": " +
      lookahead.getType() + " expected " + token);
    }
    if (active_tree) {
      Symbol label = lookahead;
      nextToken();
      return new ParseTree(label);
    } else {
      nextToken();
      return null;
    }
  }

  public ParseTree startParse() throws IOException {
    if (tree) {
      active_tree = true;
      return program();
    } else {
      program();
      return null;
    }
  }

  private ParseTree skipEndline() throws IOException {
    while (lookahead.getType().equals(LexicalUnit.ENDLINE)) {
        nextToken();
    }
    return new ParseTree("SkipLines");
  }

  private ParseTree program() throws IOException {
    System.out.print(PROGRAM);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      skipEndline(),
      compareToken(LexicalUnit.BEGINPROG),
      compareToken(LexicalUnit.PROGNAME),
      compareToken(LexicalUnit.ENDLINE),
      skipEndline(),
      variables(),
      skipEndline(),
      code(),
      skipEndline(),
      compareToken(LexicalUnit.ENDPROG),
      skipEndline(),
      compareToken(LexicalUnit.EOS)
      );
      return new ParseTree("Program", treeList);
    } else {
      skipEndline();
      compareToken(LexicalUnit.BEGINPROG);
      compareToken(LexicalUnit.PROGNAME);
      compareToken(LexicalUnit.ENDLINE);
      skipEndline();
      variables();
      skipEndline();
      code();
      skipEndline();
      compareToken(LexicalUnit.ENDPROG);
      skipEndline();
      compareToken(LexicalUnit.EOS);
      return null;
    }
  }

  private ParseTree variables() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.VARIABLES)) {
      System.out.print(VARIABLES);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.VARIABLES),
        varlist()
        );
        return new ParseTree("Variables", treeList);
      } else {
        compareToken(LexicalUnit.VARIABLES);
        varlist();
        return null;
      }
    } else {
      System.out.print(VARIABLES_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("Variables", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree varlist() throws IOException {
    System.out.print(VARLIST);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.VARNAME),
      varlistend()
      );
      return new ParseTree("Varlist", treeList);
    } else {
      compareToken(LexicalUnit.VARNAME);
      varlistend();
      return null;
    }
  }

  private ParseTree varlistend() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.COMMA)) {
      System.out.print(VARLISTEND);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.COMMA),
        compareToken(LexicalUnit.VARNAME),
        varlistend()
        );
        return new ParseTree("Varlistend", treeList);
      } else {
        compareToken(LexicalUnit.COMMA);
        compareToken(LexicalUnit.VARNAME);
        varlistend();
        return null;
      }
    } else {
      System.out.print(VARLISTEND_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("Varlistend", treeList);
      } else {
        return null;
      }
   }
  }

  private ParseTree code() throws IOException {
    switch(lookahead.getType()) {
      case VARNAME:
      case IF:
      case WHILE:
      case FOR:
      case PRINT:
      case READ:
        System.out.print(CODE);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          instruction(),
          skipEndline(),
          code()
          );
          return new ParseTree("Code", treeList);
        } else {
          instruction();
          if (lookahead.getType().equals(LexicalUnit.ENDLINE)) {
            skipEndline();
            code();
          }
          return null;
        }
      default:
        if (active_tree) {
          return new ParseTree("End");
        } else {
          return null;
        }
    }
  }

  private ParseTree instruction() throws IOException {
    switch(lookahead.getType()) {
      case VARNAME:
        System.out.print(INSTRUCTION_ASSIGN);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(assign());
          return new ParseTree("Instruction", treeList);
        } else {
          assign();
          return null;
        }
      case IF:
        System.out.print(INSTRUCTION_IF);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(parse_if());
          return new ParseTree("Instruction", treeList);
        } else {
          parse_if();
          return null;
        }
      case WHILE:
        System.out.print(INSTRUCTION_WHILE);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(parse_while());
          return new ParseTree("Instruction", treeList);
        } else {
          parse_while();
          return null;
        }
      case FOR:
        System.out.print(INSTRUCTION_FOR);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(parse_for());
          return new ParseTree("Instruction", treeList);
        } else {
          parse_for();
          return null;
        }
      case PRINT:
        System.out.print(INSTRUCTION_PRINT);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(parse_print());
          return new ParseTree("Instruction", treeList);
        } else {
          parse_print();
          return null;
        }
      case READ:
        System.out.print(INSTRUCTION_READ);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(parse_read());
          return new ParseTree("Instruction", treeList);
        } else {
          parse_read();
          return null;
        }
    }
    return null;
  }

  private ParseTree assign() throws IOException {
    System.out.print(ASSIGN);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.VARNAME),
      compareToken(LexicalUnit.ASSIGN),
      exprArith()
      );
      return new ParseTree("Assign", treeList);
    } else {
      compareToken(LexicalUnit.VARNAME);
      compareToken(LexicalUnit.ASSIGN);
      exprArith();
      return null;
    }
  }

  private ParseTree exprArith() throws IOException {
    System.out.print(EXPRARITH);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      hpProd(),
      lpExpr()
      );
      return new ParseTree("ExprArith", treeList);
    } else {
      hpProd();
      lpExpr();
      return null;
    }
  }

  private ParseTree hpProd() throws IOException {
    System.out.print(HPPROD);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      simpleExpr(),
      hpExpr()
      );
      return new ParseTree("HpProd", treeList);
    } else {
      simpleExpr();
      hpExpr();
      return null;
    }
  }

  private ParseTree hpExpr() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.TIMES) ||
        lookahead.getType().equals(LexicalUnit.DIVIDE)) {
          System.out.print(HPEXPR);
          if (active_tree) {
            List<ParseTree> treeList = Arrays.asList(
            hpOp(),
            simpleExpr(),
            hpExpr()
            );
            return new ParseTree("HpExpr", treeList);
          } else {
            hpOp();
            simpleExpr();
            hpExpr();
            return null;
          }
    } else {
      System.out.print(HPEXPR_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("HpExpr", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree lpExpr() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.PLUS) ||
        lookahead.getType().equals(LexicalUnit.MINUS)) {
          System.out.print(LPEXPR);
          if (active_tree) {
            List<ParseTree> treeList = Arrays.asList(
            lpOp(),
            hpProd(),
            lpExpr()
            );
            return new ParseTree("LpExpr", treeList);
          } else {
            lpOp();
            hpProd();
            lpExpr();
            return null;
          }
    } else {
      System.out.print(LPEXPR_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("LpExpr", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree simpleExpr() throws IOException {
    switch(lookahead.getType()) {
      case VARNAME:
        System.out.print(SIMPLEEXPR_VARNAME);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.VARNAME)
          );
          return new ParseTree("SimpleExpr", treeList);
        } else {
          nextToken();
          return null;
        }
      case NUMBER:
        System.out.print(SIMPLEEXPR_NUMBER);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.NUMBER)
          );
          return new ParseTree("SimpleExpr", treeList);
        } else {
          nextToken();
          return null;
        }
        case LPAREN:
        System.out.print(SIMPLEEXPR_PAREN);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.LPAREN),
          exprArith(),
          compareToken(LexicalUnit.RPAREN)
          );
          return new ParseTree("SimpleExpr", treeList);
        } else {
          nextToken();
          exprArith();
          compareToken(LexicalUnit.RPAREN);
          return null;
        }
      case MINUS:
      System.out.print(SIMPLEEXPR_MINUS);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.MINUS),
          exprArith()
          );
          return new ParseTree("SimpleExpr", treeList);
        } else {
          nextToken();
          exprArith();
          return null;
        }
      default:
        throw new Error("\nError at line " + lookahead.getLine() + ": " +
        lookahead.getType() + " expected a number, a variable or an arithmetic expression");
    }
  }

  private ParseTree hpOp() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.TIMES)) {
      System.out.print(HPOP_TIMES);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.TIMES)
        );
        return new ParseTree("HpOp", treeList);
      } else {
        nextToken();
        return null;
      }
    } else if (lookahead.getType().equals(LexicalUnit.DIVIDE)) {
      System.out.print(HPOP_DIVIDE);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.DIVIDE)
        );
        return new ParseTree("HpOp", treeList);
      } else {
        nextToken();
        return null;
      }
    } else {
      throw new Error("\nError at line " + lookahead.getLine() + ": " +
      lookahead.getType() + " expected multiplication or division operator");
    }
  }

  private ParseTree lpOp() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.PLUS)) {
      System.out.print(LPOP_PLUS);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.PLUS)
        );
        return new ParseTree("LpOp", treeList);
      } else {
        nextToken();
        return null;
      }
    } else if (lookahead.getType().equals(LexicalUnit.MINUS)) {
      System.out.print(LPOP_MINUS);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.MINUS)
        );
        return new ParseTree("LpOp", treeList);
      } else {
        return null;
      }
    } else {
      throw new Error("\nError at line " + lookahead.getLine() + ": " +
      lookahead.getType() + " expected addition or substraction operator");
    }

  }

  private ParseTree parse_if() throws IOException {
    System.out.print(IF);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.IF),
      compareToken(LexicalUnit.LPAREN),
      cond(),
      compareToken(LexicalUnit.RPAREN),
      compareToken(LexicalUnit.THEN),
      skipEndline(),
      code(),
      ifElse(),
      compareToken(LexicalUnit.ENDIF)
      );
      return new ParseTree("If", treeList);
    } else {
      compareToken(LexicalUnit.IF);
      compareToken(LexicalUnit.LPAREN);
      cond();
      compareToken(LexicalUnit.RPAREN);
      compareToken(LexicalUnit.THEN);
      skipEndline();
      code();
      ifElse();
      compareToken(LexicalUnit.ENDIF);
      return null;
    }
  }

  private ParseTree ifElse() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.ELSE)) {
      System.out.print(IFELSE);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.ELSE),
        compareToken(LexicalUnit.ENDLINE),
        code()
        );
        return new ParseTree("IfElse", treeList);
      } else {
        compareToken(LexicalUnit.ELSE);
        compareToken(LexicalUnit.ENDLINE);
        code();
        return null;
      }
    } else {
      System.out.print(IFELSE_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("IfElse", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree cond() throws IOException {
    System.out.print(COND);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      pCond(),
      lpCond()
      );
      return new ParseTree("Cond", treeList);
    } else {
      pCond();
      lpCond();
      return null;
    }
  }

  private ParseTree pCond() throws IOException {
    System.out.print(PCOND);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      simpleCond(),
      hpCond()
      );
      return new ParseTree("PCond", treeList);
    } else {
      simpleCond();
      hpCond();
      return null;
    }
  }

  private ParseTree hpCond() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.AND)) {
      System.out.print(HPCOND_AND);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.AND),
        simpleCond(),
        hpCond()
        );
        return new ParseTree("HpCond", treeList);
      } else {
          compareToken(LexicalUnit.AND);
          simpleCond();
          hpCond();
          return null;
        }
    } else {
      System.out.print(HPCOND_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("HpCond", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree lpCond() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.OR)) {
      System.out.print(LPCOND_OR);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.OR),
        pCond(),
        lpCond()
        );
        return new ParseTree("LpCond", treeList);
      } else {
        compareToken(LexicalUnit.OR);
        pCond();
        lpCond();
        return null;
      }
    } else {
      System.out.print(LPCOND_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("LpCond", treeList);
      } else {
        return null;
      }
    }
  }

  private ParseTree simpleCond() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.NOT)) {
      System.out.print(SIMPLECOND_NOT);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.NOT),
        simpleCond()
        );
        return new ParseTree("SimpleCond", treeList);
      } else {
        compareToken(LexicalUnit.NOT);
        simpleCond();
        return null;
      }
    } else {
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        exprArith(),
        comp(),
        exprArith()
        );
        return new ParseTree("SimpleCond", treeList);
      } else {
        exprArith();
        comp();
        exprArith();
        return null;
      }
    }
  }

  private ParseTree comp() throws IOException {
    switch(lookahead.getType()) {
      case EQ:
        System.out.print(COMP_EQ);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.EQ)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.EQ);
          return null;
        }
      case GEQ:
        System.out.print(COMP_GEQ);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.GEQ)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.GEQ);
          return null;
        }
      case GT:
        System.out.print(COMP_GT);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.GT)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.GT);
          return null;
        }
      case LEQ:
        System.out.print(COMP_LEQ);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.LEQ)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.LEQ);
          return null;
        }
      case LT:
        System.out.print(COMP_LT);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.LT)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.LT);
          return null;
        }
      case NEQ:
        System.out.print(COMP_NEQ);
        if (active_tree) {
          List<ParseTree> treeList = Arrays.asList(
          compareToken(LexicalUnit.NEQ)
          );
          return new ParseTree("Comp", treeList);
        } else {
          compareToken(LexicalUnit.NEQ);
          return null;
        }
      default:
        throw new Error("\nError at line " + lookahead.getLine() + ": " +
        lookahead.getType() + " expected a comparaison operator");
    }
  }

  private ParseTree parse_while() throws IOException {
    System.out.print(WHILE);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.WHILE),
      cond(),
      compareToken(LexicalUnit.DO),
      skipEndline(),
      code(),
      compareToken(LexicalUnit.ENDWHILE)
      );
      return new ParseTree("While", treeList);
    } else {
      compareToken(LexicalUnit.WHILE);
      cond();
      compareToken(LexicalUnit.DO);
      skipEndline();
      code();
      compareToken(LexicalUnit.ENDWHILE);
      return null;
    }
  }

  private ParseTree parse_for() throws IOException {
    System.out.print(FOR);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.FOR),
      compareToken(LexicalUnit.VARNAME),
      compareToken(LexicalUnit.ASSIGN),
      exprArith(),
      compareToken(LexicalUnit.TO),
      exprArith(),
      compareToken(LexicalUnit.DO),
      skipEndline(),
      code(),
      compareToken(LexicalUnit.ENDFOR)
      );
      return new ParseTree("For", treeList);
    } else {
      compareToken(LexicalUnit.FOR);
      compareToken(LexicalUnit.VARNAME);
      compareToken(LexicalUnit.ASSIGN);
      exprArith();
      compareToken(LexicalUnit.TO);
      exprArith();
      compareToken(LexicalUnit.DO);
      skipEndline();
      code();
      compareToken(LexicalUnit.ENDFOR);
      return null;
    }
  }

  private ParseTree parse_print() throws IOException {
    System.out.print(PRINT);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.PRINT),
      compareToken(LexicalUnit.LPAREN),
      exprList(),
      compareToken(LexicalUnit.RPAREN)
      );
      return new ParseTree("Print", treeList);
    } else {
      compareToken(LexicalUnit.PRINT);
      compareToken(LexicalUnit.LPAREN);
      exprList();
      compareToken(LexicalUnit.RPAREN);
      return null;
    }
  }

  private ParseTree parse_read() throws IOException {
    System.out.print(READ);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      compareToken(LexicalUnit.READ),
      compareToken(LexicalUnit.LPAREN),
      varlist(),
      compareToken(LexicalUnit.RPAREN)
      );
      return new ParseTree("Read", treeList);
    } else {
      compareToken(LexicalUnit.READ);
      compareToken(LexicalUnit.LPAREN);
      varlist();
      compareToken(LexicalUnit.RPAREN);
      return null;
    }
  }

  private ParseTree exprList() throws IOException {
    System.out.print(EXPLIST);
    if (active_tree) {
      List<ParseTree> treeList = Arrays.asList(
      exprArith(),
      expListEnd()
      );
      return new ParseTree("ExprList", treeList);
    } else {
      exprArith();
      expListEnd();
      return null;
    }
  }

  private ParseTree expListEnd() throws IOException {
    if (lookahead.getType().equals(LexicalUnit.COMMA)) {
      System.out.print(EXPLISTEND);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(
        compareToken(LexicalUnit.COMMA),
        exprList()
        );
        return new ParseTree("ExpListEnd", treeList);
      } else {
        compareToken(LexicalUnit.COMMA);
        exprList();
        return null;
      }
    } else {
      System.out.print(EXPLISTEND_EPSILON);
      if (active_tree) {
        List<ParseTree> treeList = Arrays.asList(new ParseTree("EPSILON"));
        return new ParseTree("ExpListEnd", treeList);
      } else {
        return null;
      }
    }
  }

}
