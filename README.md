# DXFDetailer
Project for price quotes based on DXF File.

Currently using kabeja library for DXF File reading.

## Modules
- reader-api: Contains commons packages
- desktop-application: Contains all required packaging for desktop app deployment

To be completed: 
- web-app modules

# Desktop Application
As a warning, before compiling you **may** need to define an SDK for IDE runtime components. (JavaFX as no longer included in SDK)

- Compile using mvn compile package
- Located file inside target/shade
- Can be ran using java -jar <path>

Upon release, application gets bundled to native .app for macos alongside dock icon.
