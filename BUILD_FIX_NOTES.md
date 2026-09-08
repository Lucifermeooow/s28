# Stream 22 Build Fix

This repository already contains the Android application module under `app/`.
The CI workflow therefore builds `:app:assembleRelease` from the repository root.

The current project uses Android Gradle Plugin 9.1.1, so CI uses Gradle 9.7.1.
The project's release signing points at the root `debug.keystore`; CI generates
that temporary keystore during the run and never commits it.

No application source, dependencies, Red5, Supabase, Facebook, or Google code
was changed by this build fix.
