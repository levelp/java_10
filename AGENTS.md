# Agent Instructions

This document provides instructions for AI agents working on this project.

## Codev Methodology

This project uses the Codev context-driven development methodology.

### Active Protocol
- Protocol: SPIDER-SOLO (single-agent variant)
- Location: codev/protocols/spider-solo/protocol.md

### Core Principles
1. **Context Drives Code** - Context definitions flow from high-level specifications down to implementation details
2. **Human-AI Collaboration** - Designed for seamless cooperation between developers and AI agents
3. **Evolving Methodology** - The process itself evolves and improves with each project

### Directory Structure
- Specifications: codev/specs/
- Plans: codev/plans/
- Reviews: codev/reviews/
- Resources: codev/resources/
- Protocols: codev/protocols/

### Development Workflow

The SPIDER-SOLO protocol follows this workflow:

1. **Specification Phase**
   - Create detailed specifications in `codev/specs/####-descriptive-name.md`
   - Use four-digit numbering (e.g., 0001, 0002)
   - Document requirements, constraints, and success criteria

2. **Planning Phase**
   - Create implementation plans in `codev/plans/####-descriptive-name.md`
   - Break down work into manageable tasks
   - Identify dependencies and risks

3. **IDE Loop** (Implementation, Defense, Evaluation)
   - **I**mplement: Build the code
   - **D**efend: Write comprehensive tests
   - **E**valuate: Verify requirements are met

4. **Review Phase**
   - Document lessons learned in `codev/reviews/####-descriptive-name.md`
   - Capture what worked and what didn't
   - Update protocol if needed

### File Naming Convention
All Codev documents use the format: `####-descriptive-name.md`
- Four-digit sequential number
- Hyphen-separated descriptive name
- Markdown format

### Git Integration
- Each stage gets one pull request
- Phases can have multiple commits
- User approval required before PRs

See codev/protocols/spider-solo/protocol.md for full protocol details.
