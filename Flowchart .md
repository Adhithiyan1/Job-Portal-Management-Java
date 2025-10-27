flowchart TD
    A[Start: Job Portal] --> B[User Type?]
    
    B --> C[Job Seeker]
    B --> D[Employer]
    B --> E[Admin]
    
    %% Job Seeker Flow
    C --> F[Register/Login]
    F --> G[Browse Jobs]
    G --> H{Search Filters?}
    H -->|Yes| I[Apply Filters<br>Location, Salary, etc.]
    H -->|No| J[View All Jobs]
    I --> J
    J --> K[View Job Details]
    K --> L{Apply?}
    L -->|Yes| M[Submit Application<br>Upload Resume]
    L -->|No| G
    M --> N[Track Applications]
    N --> O[Receive Notifications]
    O --> P[Logout]
    
    %% Employer Flow
    D --> Q[Register/Login<br>Company Verification]
    Q --> R[Manage Company Profile]
    R --> S[Post New Job]
    S --> T[Set Job Requirements<br>& Description]
    T --> U[Review Applications]
    U --> V{Application Decision?}
    V -->|Accept| W[Schedule Interview]
    V -->|Reject| X[Send Rejection Email]
    W --> Y[Update Application Status]
    X --> Y
    Y --> Z[Logout]
    
    %% Admin Flow
    E --> AA[Admin Login]
    AA --> AB[Dashboard]
    AB --> AC{Management Options}
    AC --> AD[User Management]
    AC --> AE[Job Listings<br>Moderation]
    AC --> AF[Company<br>Verifications]
    AC --> AG[Reports & Analytics]
    
    AD --> AH[Approve/Suspend Users]
    AE --> AI[Review/Remove Jobs]
    AF --> AJ[Verify Companies]
    AG --> AK[Generate Reports]
    
    AH --> AL[Update System]
    AI --> AL
    AJ --> AL
    AK --> AL
    AL --> AM[Admin Logout]
    
    %% Common End Point
    P --> AN[End Session]
    Z --> AN
    AM --> AN
    
    style A fill:#2E8B57,color:white
    style C fill:#4169E1,color:white
    style D fill:#FF8C00,color:white
    style E fill:#DC143C,color:white
    style AN fill:#666666,color:white
