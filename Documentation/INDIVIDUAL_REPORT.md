# Individual Report

## Contributions

- **Feature Implementation**  
  - Added “Purchase a Membership” flow for Members, including database schema changes and UI prompts.  
  - Extended the Admin menu to include “Delete a User” and “View Total Revenue,” wiring DAO, Service, and UI layers.  
  - Fixed numerous import and syntax errors (e.g. missing `import org.keyin.memberships.Membership;`, typos in method names and menu options).

- **Bug Testing & Fixes**  
  - Diagnosed and resolved the “relation ‘memberships’ does not exist” error by creating and altering the PostgreSQL `memberships` table.  
  - Walked through Maven compile/run steps to ensure all classes were being compiled and picked up (clean, compile, exec).  
  - Corrected small but critical mistakes (missing semicolons, wrong method signatures, mis‑spelled helper methods).

- **Documentation**  
  - Wrote the **User Guide** (USER_GUIDE.md) covering installation, running the app, and role walkthroughs.  
  - Wrote the **Development Documentation** (DEVELOPMENT_DOCUMENTATION.md) including project structure, build instructions, Javadoc steps, and database setup.  
  - Created SQL scripts (`scripts.sql`) and embedded usage examples for psql.

## Challenges

- **Getting Started**  
  - Even with a template repo, deciding where to begin—project architecture, database schema, or console menus—took extra time.  
  - Once the first features were in place, momentum picked up.

- **Documentation Struggles**  
  - Writing clear, step‑by‑step instructions for complete novices was tougher than expected.  
  - Admitted to being “stuck” on formatting and content structure.

- **Time Pressure**  
  - Faced tight deadlines near the end; commit frequency dropped as I raced to finalize features and docs.  
  - Typos and small syntax mistakes crept in under pressure, leading to extra debugging.

## Use of ChatGPT

I leveraged **ChatGPT** to:

- Helped create the **User Guide** and **Development Documentation** as I am not entirely comfortable with that just yet.  
- Troubleshoot Maven compilation issues and database errors.  
- Help build upon code snippets for the Admin delete‑user feature and membership purchase flow.  

While I wrote and tested the code myself, ChatGPT and Google provided guidance, examples, and formatting advice—especially for the documentation sections.

## Lessons Learned

- **Break problems into small steps:** Tackling one menu or one SQL error at a time proved far more manageable than trying to solve everything at once.  
- **Documentation is code, too:** A working application only counts once users (and graders) can follow the setup and run instructions without frustration.  
- **Consistency matters:** Early on I fixed typos and name mismatches; keeping method names and imports consistent saved many compile‑run cycles.

---

